package com.enigma.lcloanmanajementsystem.entity;

import com.enigma.lcloanmanajementsystem.utils.enums.EmployeeStatus;
import com.enigma.lcloanmanajementsystem.utils.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loan")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public LoanEntity(Integer tenor, UserEntity user, Long loanAmount, String purpose, Long monthlyIncome, Long monthlyExpenditure, EmployeeStatus employeeStatus, Integer creditScore, String creditRecommendation, LoanStatus status) {
        this.tenor = tenor;
        this.user = user;
        this.loanAmount = loanAmount;
        this.purpose = purpose;
        this.monthlyIncome = monthlyIncome;
        this.monthlyExpenditure = monthlyExpenditure;
        this.employeeStatus = employeeStatus;
        this.creditScore = creditScore;
        this.creditRecommendation = creditRecommendation;
        this.status = status;
    }

    //    user
    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

//    loanAmount
    @Column(name = "loan_amount")
    private Long loanAmount;

//    tenor
    private Integer tenor;

//    purpose
    private String purpose;

//    monthlyIncome
    @Column(name = "monthly_income")
    private Long monthlyIncome;

    @Column(name = "monthly_expenditure")
    private Long monthlyExpenditure;

//    employmentStatus
    @Enumerated(EnumType.STRING)
    @Column(name = "employe_status")
    private EmployeeStatus employeeStatus;

//    status
    @Enumerated(EnumType.STRING)
    private LoanStatus status;

//    creditScore
    @Column(name = "credit_score")
    private  Integer creditScore;

//    creditRecommendation
    @Column(name = "credit_rekomendation")
    private String creditRecommendation;

    @Override
    public String toString() {
        return "LoanEntity{" +
                "id=" + id +
                ", loanAmount=" + loanAmount +
                ", tenor=" + tenor +
                ", purpose='" + purpose + '\'' +
                ", monthlyIncome=" + monthlyIncome +
                ", monthlyExpenditure=" + monthlyExpenditure +
                ", employeeStatus=" + employeeStatus +
                ", status=" + status +
                ", creditScore=" + creditScore +
                ", creditRecommendation='" + creditRecommendation + '\'' +
                '}';
    }
}
