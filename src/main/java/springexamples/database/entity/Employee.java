package springexamples.database.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="office_id")
	private Integer officeId;
	
	@Column(name="lastname")
	private String lastName;
	
	@Column(name="firstname")
	private String firstName;
	
	@Column(name="extension")
	private String extension;
	
	@Column(name="email")
	private String email;
	
	@Column(name="reports_to")
	private Integer reportsTo;
	
	@Column(name="job_title")
	private String jobTitle;

	@Column(name="vacation_hours")
	private Integer vacationHours;

	@Column(name="profile_image_url")
	private String profileImageUrl;
	
	// Map 1 to many relationship from employee to customer
	@ToString.Exclude
	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Customer> customers;
	
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", officeId=" + officeId + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", extension=" + extension + ", email=" + email + ", reportsTo=" + reportsTo + ", jobTitle="
				+ jobTitle + ", vacationHours=" + vacationHours + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
