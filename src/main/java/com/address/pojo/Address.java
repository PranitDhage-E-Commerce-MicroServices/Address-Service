package com.address.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@Getter
@Setter
@ToString
@Entity
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"user"}, allowSetters = true)  //it will not send password to client side
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "add_id")
    @JsonProperty("addId")
    private Integer addId;

    @Column(name = "address")
    @JsonProperty("address")
    @NonNull
    @Length(min = 10, max = 50, message = "Address length must be between 10 to 50 characters")
    private String address;

    @Column(name = "city")
    @JsonProperty("city")
    @Length(min = 5, max = 10, message = "City length must be between 5 to 10 characters")
    private String city;

    @Column(name = "state")
    @JsonProperty("state")
    private String state;

    @Column(name = "country")
    @JsonProperty("country")
    private String country;

    @Column(name = "pin")
    @JsonProperty("pin")
    private String pin;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @CreationTimestamp
    @JsonProperty("addedOn")
    @Column(name = "added_on", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "user_id")
    @JsonProperty("userId")
    private Integer userId;

}
