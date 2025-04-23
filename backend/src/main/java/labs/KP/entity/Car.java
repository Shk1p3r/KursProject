package labs.KP.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "car", schema = "public")
@Entity
public class Car {
    @Id
    @SequenceGenerator(name = "car_seq", sequenceName = "car_id_car_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="car_seq")
    @Column(name = "id_car")
    private int id;
    @Column(name = "mark")
    private String mark;
    @Column(name = "model")
    private String model;
    @Column(name = "year_of_production")
    private int yearOfProduction;
    @Column(name = "license_plate_number", unique = true)
    private String licensePlateNumber;
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Lesson> lessons = new ArrayList<>();;
}
