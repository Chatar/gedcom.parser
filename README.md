GEDCOM Parser Challenge
GEDCOM is the "GEnealogical Data COMmunication" file format. It is a plain-text
electronic format used to transfer genealogical data.
The purpose of this challenge is to develop a simple parser that can convert a GEDCOM
file to XML.
GEDCOM Format
The GEDCOM file format is very straightforward. Each line represents a node in a tree. It
looks something like this:
! 0 @I1@ INDI
! 1 NAME Jamis Gordon /Buck/
! 2 SURN Buck
! 2 GIVN Jamis Gordon
! 1 SEX M
! ...
In general, each line is formatted thus:
! LEVEL TAG-OR-ID [DATA]
The LEVEL is an integer, representing the current depth in the tree. If subsequent lines
have greater levels than the current node, they are children of the current node.
TAG-OR-ID is either a tag that identifies the type of data in that node, or it is a unique
identifier. Tags are 3- or 4-letter words in uppercase. The unique identifiers are always text
surrounded by "@" characters (i.e., "@I54@"). If an ID is given, the DATA is the type of the
subtree that is identified.
So, to take the example given above apart:
1. "0 @I1@ INDI". This starts a new subtree of type INDI (individual). The id for this
individual is "@I1@".
2. "1 NAME Jamis Gordon /Buck/". This starts a NAME subtree with a value of
"Jamis Gordon /Buck/".
3. "2 SURN Buck". This is a subelement of the NAME subtree, of type SURN
("surname").
4. "2 GIVN Jamis Gordon". This is a subelement of the NAME subtree, of type GIVN
(“given name”).
5. "1 SEX M". Creates a new subelement of the INDI element, of type "SEX" (i.e.,
"gender").
And so forth...
Variable whitespace is allowed between the level and the tag. Blank lines are ignored.
The Challenge
The challenge, then, is to create a parser that takes a GEDCOM file as input and converts
it to XML. The snippet of GEDCOM given above would become:
! <gedcom>
! ! <indi id="@I1@">
! ! ! <name value="Jamis Gordon /Buck/">
! ! ! ! <surn>Buck</surn>
! ! ! ! <givn>Jamis Gordon</givn>
! ! ! </name>
! ! ! <sex>M</sex>
! ! ! ...
! ! </indi>
! ! ...
! </gedcom>
Sample Input
There is an accompanying GEDCOM file with this challenge containing the lineage of
various European royalty.

Solution:

The approach is simple with Single Responsibility principal.

1. First a FileParser parse the file into list of entity where each entity is a group of records 
  and all records within an entry belongs to the same entity.
2. In second statge, an EntityTree is build using list of Entities.

//                                                                  gedcom
//																				                            |
//																				                            |
//																				                            |
//0 @I0001@ INDI                                                    @10001@   
//1 NAME Elizabeth Alexandra Mary /Windsor/          					    /	   |     \
//1 SEX F																                         /     |      \
//1 BIRT															                          /		   |		   \										       
//2 DATE 21 Apr 1926												                   /       |        \
//2 PLAC 17 Bruton Street, London, W1			NAME------SEX-------BIRT----OCCU-------FAMC-------FAMS-------NOTE-------CHAN
//1 OCCU Queen														                    /\													                         |	
//1 FAMC @F0003@                                             /  \			           									                 |
//1 FAMS @F0001@                                            /    \													                       |
//1 NOTE @N0002@                                           /      \													                       |
//1 CHAN													                       DATE	   PLACE											                      DATE
//2 DATE 13 Dec 2003

  
3. In final and third stage an xml is created using EntityTree.  

Assumptions - 
1. The root of the records starts with 0.
2. The delimiter between reords is single or more spaces.

Each unit of functionality is testable with one integrationg test EndToEndTest to test entire functionality. 

