package app.vinhomes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(
        name = "tbl_account",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "email_unique_constraint",
                        columnNames = "email"
                ),
                @UniqueConstraint(
                        name = "account_name_constraint",
                        columnNames = "account_name"
                )
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "pwd", nullable = false)
    private String password;

    private String email;

    private String firstName;

    private String lastName;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date dob; //date of birth

    private int role;

    @OneToOne(
            optional = false,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "address_id",
            referencedColumnName = "address_id",
            nullable = false
    )
    private Address address;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL
    )
    private List<Phone> phones;
    public void addPhone(Phone phone) {
        if(phones == null) {
            phones = new ArrayList<>();
            phones.add(phone);
        } else phones.add(phone);
    }

    @ManyToOne
    @JoinColumn(
            name = "job",
            referencedColumnName = "servicecate_id"
    )
    private ServiceCategory serviceCategory;
}