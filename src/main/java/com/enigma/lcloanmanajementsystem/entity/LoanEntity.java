package com.enigma.lcloanmanajementsystem.entity;

import com.enigma.lcloanmanajementsystem.utils.enums.EmployeeStatus;
import com.enigma.lcloanmanajementsystem.utils.enums.LoanStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "loan")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    user
    @ManyToOne(fetch = FetchType.LAZY)
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
                ", user=" + user +
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
