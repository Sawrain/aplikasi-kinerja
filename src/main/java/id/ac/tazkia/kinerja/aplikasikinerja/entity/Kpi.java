package id.ac.tazkia.kinerja.aplikasikinerja.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @Getter @Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Kpi {

    @Id
    @GeneratedValue(generator = "uuid" )
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @Column(name = "key_result",nullable = false)
    @NotNull
    private String keyResult;

    @Min(0)
    private BigDecimal weight;

    @Min(0)
    private BigDecimal baseLine;

    @Min(0)
    private BigDecimal target;

    @JsonBackReference
    @ManyToMany(mappedBy = "kpi")
    private Set<StaffRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "kpi")
    @JsonBackReference
    private List<Indicators> indicatorsList = new ArrayList<>();

    @NotNull @Enumerated(EnumType.STRING)
    private StatusKpi status= StatusKpi.AKTIF;

}
