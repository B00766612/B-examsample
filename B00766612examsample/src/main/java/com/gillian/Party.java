package com.gillian;

class Party
{
    private String partyName;
    private Integer people;
    private String alcohol;
    private String feature;

    Party(String partyName, Integer people, String alcohol, String feature)
    {
        this.partyName = partyName;
        this.people = people;
        this.alcohol = alcohol;
        this.feature = feature;
    }
	//partyName
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	//people
	public Integer getPeople() {
		return people;
		
	}
	public void setPeople(Integer people) {
		this.people = people;
	}
	//getAlcohol
	public String getAlcohol() {
		return alcohol;
	}
	
	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}
	
	public String getFeature() {
		return feature;
	}
	
	public void setAmount(String feature) {
		this.feature = feature;
	}
}