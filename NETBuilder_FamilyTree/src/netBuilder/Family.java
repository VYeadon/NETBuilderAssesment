package netBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Family {
	
	public static void main(String[] args)
	{
		Family fam = new Family();
		fam.runner();

	}
	
	// Runs example code given in the assessment brief
	public void runner() {
		System.out.println(this.setParent("Frank", "Morgan"));
		
		System.out.println(this.setParent("Frank", "Dylan"));
		System.out.println(this.male("Dylan"));
		System.out.println(this.setParent("Joy", "Frank"));
		System.out.println(this.male("Frank"));
		System.out.println(this.male("Morgan"));
		System.out.println(this.setParent("July", "Morgan"));
		System.out.println(this.isMale("Joy") || this.isFemale("Joy"));
		
		System.out.println(this.getChildren("Morgan"));
		System.out.println(this.setParent("Jennifer", "Morgan"));
		System.out.println(this.getChildren("Morgan"));
		System.out.println(this.getChildren("Dylan"));
		System.out.println(this.getParent("Frank"));
		System.out.println(this.setParent("Morgan", "Frank"));
		
	}
	//  Name,       gender,              parents,               children
	Map<String, String>
	nameMap = new HashMap<String, String>();
	
	Map<String, String>
	genderMap = new HashMap<String, String>();
	
	Map<String, String>
	partnerMap = new HashMap<String, String>();
	
	Map<String, ArrayList<String>>
	parentMap = new HashMap<String, ArrayList<String>>();
	
	Map<String, ArrayList<String>>
	childMap = new HashMap<String, ArrayList<String>>();
	
	// checks if name and exists and sets
	public void checkSetName(String name)
	{
		if(!nameMap.containsKey(name)) 
		{
			nameMap.put(name, name);
		}
	}
	
	// Checks if name has a partner and if so returns it
	public String getPartner(String name)
	{
		if(partnerMap.containsKey(name))
		{
			return partnerMap.get(name).toString();
		}
		return "";
		
	}
	
	// checks partners gender and sets gender accordingly
	public void checkSetGender(String nameToBeSet)
	{		
		String partnerName = getPartner(nameToBeSet);
		
		if(genderMap.get(partnerName) == "female") 
		{
			genderMap.put(nameToBeSet, "male");
		}
		
		if(genderMap.get(partnerName) == "male") 
		{
			genderMap.put(nameToBeSet, "female");
		}
	}
	
// ---- My functions above /\ --------------------------- Required functions below \/ -----
	
	public boolean setParent(String childName, String parentName)
	{
		boolean parentSet = false;
		
		checkSetName(childName);
		checkSetName(parentName);
		
		ArrayList<String> parents = parentMap.get(childName);
		ArrayList<String> children = childMap.get(parentName);
		
		// Checks if the parent to be set is a child of the child to be set
		if (!childMap.containsKey(childName)) {
			if (children == null) {
				// resets ArrayList from null to stop null pointer
				children = new ArrayList<String>();
			}
			// If both parents have not already been set
			if (parents == null) {
				// resets ArrayList from null to stop null pointer			
				parents = new ArrayList<String>();

				parentSet = true;
			}
			// if one parent has already been set
			else if (parents.size() == 1) {
				// Checks if parents aren't the same gender
				if (genderMap.get(parentName) != genderMap.get(parents.get(0).toString())
						| genderMap.get(parentName) == null)
				{
					// sets the partners of the different parents
					partnerMap.put(parentName, parents.get(0).toString());
					partnerMap.put(parents.get(0).toString(), parentName);
					// Sets gender if information is not already supplied
					checkSetGender(parentName);

					parentSet = true;
				}
			}
			
			// If the correct conditions are met adds the parent and children to the tree
			if (parentSet == true) {
				// sets the child's new parent
				parents.add(parentName);
				parentMap.put(childName, parents);
				// Sets the parents new child
				children.add(childName);
				childMap.put(parentName, children);
			} 
		}
		return parentSet;
		
	}
	
	public boolean male(String name)
	{
		boolean genderSet = false;
		
		checkSetName(name);
		
		// if there is no gender and the partners gender is not male make gender male
		if(genderMap.get(name) == null & genderMap.get(partnerMap.get(name)) != "male") 
		{
			genderMap.put(name, "male");
		
			// Sets the gender for partner if there is one
			checkSetGender(getPartner(name));
			
			genderSet = true;
		}
		else if(genderMap.get(name).toString() == "male")
		{
			genderSet = false;
		}
		
		return genderSet;

	}
	
	public boolean female(String name)
	{
		boolean genderSet = false;
		
		checkSetName(name);
		
		if(genderMap.get(name) == null) 
		{
			genderMap.put(name, "female");
			
			// Sets the gender for partner if there is one
			checkSetGender(getPartner(name));
			
			genderSet = true;
		}
		else if(genderMap.get(name).toString() == "female")
		{
			genderSet = false;
		}
		
		return genderSet;
	}
	
	public boolean isMale(String name)
	{
		checkSetName(name);
		
		// if person is male return true else return false
		if(genderMap.get(name) == "male") 
		{
			System.out.println(genderMap.get(name));
			return true;
		}
		
		return false;
	}
	
	public boolean isFemale(String name)
	{
		checkSetName(name);
		
		// if person is female return true else return false
		if(genderMap.get(name) == "female") 
		{
			return true;
		}
		
		return false;
	}
	
	public String getParent(String name)
	{
		checkSetName(name);
		
		ArrayList<String> parents = parentMap.get(name);
		
		parents.sort(null);
		
		return parents.toString();
	}
	
	public String getChildren(String name)
	{
		checkSetName(name);
		
		ArrayList<String> children = childMap.get(name);
		
		children.sort(null);
		
		return children.toString();

	}

}
