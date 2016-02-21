/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.23.0-2f66a7f modeling language!*/

package ca.mcgill.ecse321.group01.homeaudiosystem.model;

// line 48 "../../../../../../../../../ump/tmp960453/model.ump"
// line 92 "../../../../../../../../../ump/tmp960453/model.ump"
public class Genre
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Genre Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Genre(String aName)
  {
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getName()
  {
    return name;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "]"
     + outputString;
  }
}