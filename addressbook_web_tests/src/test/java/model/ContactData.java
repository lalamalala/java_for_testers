package model;

public record ContactData(String firstname, String middlename, String lastname) {
    public ContactData() {
        this("", "", "");
    }

    public ContactData withFirstName(String firstname) {return new ContactData(firstname, this.middlename, this.lastname);}

    public ContactData withMiddleName(String middlename) {return new ContactData(this.firstname, middlename, this.lastname);}

    public ContactData withLastName(String lastname) {return new ContactData(this.firstname, this.middlename, lastname);}
}