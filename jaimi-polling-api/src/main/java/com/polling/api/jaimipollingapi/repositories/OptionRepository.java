package com.polling.api.jaimipollingapi.repositories;

import com.polling.api.jaimipollingapi.model.Option;
import org.springframework.data.repository.CrudRepository;

//CrudRepository and thereby inherits all of its CRUD methods. Because the OptionRepository works with the Option domain object, it passes Option and Long as
//generic parameter values

public interface OptionRepository extends CrudRepository<Option, Long> {
}
