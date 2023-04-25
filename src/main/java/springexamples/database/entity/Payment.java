package springexamples.database.entity;

import java.util.Date;			// Used java.sql.Date before

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="customer_id", insertable=false, updatable=false)
	private Integer customerId;
	
	@Column(name="check_number")
	private String checkNumber;
	
	@Column(name="payment_date")		// Date here
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	
	@Column(name="amount", columnDefinition="decimal", precision=10, scale=2)
	private Double amount;
	
	/* To Customer */
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;




	
	
	
	
	
	
}
