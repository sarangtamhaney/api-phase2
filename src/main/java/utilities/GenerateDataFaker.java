package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;

public class GenerateDataFaker {
    Faker faker = new Faker();

    // WEIGHT,HEIGHT,DATE,CLOTHING,BIRTH SIGNS,
    // NEARBY POLICE STATION,NEARBY NGO,INCIDENT BRIEF,FILE
    // not there we can put it manually in excel sheet.

    public String getNickName() {
        String nickname=faker.name().name();
        return nickname;
    }

    public int getAge() {
        int age=faker.number().numberBetween(0, 100);
        return age;
    }

    public String getRequestId() {
        int request_id=faker.number().numberBetween(1000000, 99999999);
        return String.valueOf(request_id);
    }

    public String getDate() {
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String obtained_date=f.format(new Date());
        obtained_date+="Z";
        System.out.println(obtained_date);
        return obtained_date;
    }


    public String getEmail() {
        String email=faker.internet().emailAddress();
        return email;
    }

    public String getPassword() {
        String password=faker.internet().password();
        return password;
    }

    public int getUserId() {
        int userId=faker.number().randomDigit();
        return userId;
    }

    public String getGender() {
        String gender=faker.dog().gender();
        return gender;
    }

    public String getFullName() {
        String fullName=faker.name().fullName();
        return fullName;
    }

    public String getContactNumber() {
        String contactNumber=faker.phoneNumber().phoneNumber();
        return contactNumber;
    }

    public String getCountryCode() {
        String countryCode=faker.country().countryCode2();
        return countryCode;
    }

    public String getAddress() {
        String address=faker.address().fullAddress();
        return address;

    }

    public String getStreetAddress() {
        String streetAddress=faker.address().streetAddress();
        return streetAddress;
    }

    public String getPinCode() {
        String pinCode=faker.address().zipCode();
        return pinCode;
    }

    public String getLocation() {
        String city=faker.address().cityName();
        faker.address().cityName();
        return city;
    }
    public String getRelation() {
        String relation=faker.relationships().any();
        return relation;
    }

	/*
	public String getLanguage() {
		String language=faker.options().
	}*/

    public String getLandMarkSigns() {
        String landmark=faker.address().streetAddress();
        return landmark;
    }




    public String getCountry() {
        String country=faker.country().name();
        return country;
    }

	/*
	public int getAge() {
		String age=faker.dog().age();
		return (int)age;
	}*/

	/*
	public int getHeight() {
		String height=faker.business().
	}*./



	/*
	public String getDate() {
		DateAndTime from=faker.date();
		DateAndTime to=faker.date();
		while(from==to) {
			to=faker.date();
		}
		Date date=faker.date().between(from., null)

		return date.toLocaleString();
	}*/

    // METHOD CAN BE USED FOR FIELDS (allow_connect_police_NGO,self_verification,community_terms)
    public Boolean getBooleanValue() {
        Boolean value=faker.random().nextBoolean();
        return value;
    }


	/*
	public File getFile() {
		File newFile=faker.file().f
	}*/

}