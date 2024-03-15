package java12.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {

    private String name;
    private String country;
    private String address;
    private String phoneNumber;

//    public Company build(){
//        Company company = new Company();
//        company.setName(this.name);
//        company.setCountry(this.country);
//        company.setAddress(this.address);
//        company.setPhoneNumber(this.phoneNumber);
//        return company;
//    }
}
