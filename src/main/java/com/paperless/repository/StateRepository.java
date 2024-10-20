package com.paperless.repository;

import com.paperless.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State,Long> {

    List<State> findAllByShowInWebsite(boolean showInWebsite);

    Optional<State> findByName(String name);

//    List<StateInfo> findByIsDeletedFalseAndShowInWebsite(boolean showInWebsite);

}
