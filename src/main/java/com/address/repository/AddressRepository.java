package com.address.repository;

import com.address.exceptions.SystemException;
import com.address.pojo.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@PropertySources({
        @PropertySource("classpath:sql.properties")
})
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Value("${ADDRESS.FIND_ALL_BY_USER_USER_ID:}")
    String FIND_ALL_BY_USER_USER_ID = "";

    @Query(value = FIND_ALL_BY_USER_USER_ID)
    List<Address> findAllByUserId(@Param("userId") int userId) throws SystemException;

}
