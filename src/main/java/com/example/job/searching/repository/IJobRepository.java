package com.example.job.searching.repository;

import com.example.job.searching.model.Job;
import com.example.job.searching.model.JobType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IJobRepository extends CrudRepository<Job, Integer> {

    List<Job> findByJobType(JobType jobType);

    List<Job> findByTitleContainingIgnoreCase(String title);

    List<Job> findTop10ByOrderByAppliedDateDesc();

    List<Job> findByCompanyNameContainingIgnoreCase(String companyName);

    @Modifying
    @Query(value = "UPDATE Job  SET Job_Type = :jobType WHERE id = :id",nativeQuery = true)
    void updateJobTypeById(int id,  String jobType);


    @Modifying
    @Query(value = "update job set Salary=:salary where id=:id",nativeQuery = true)
    void updateSalary(int id, Double salary);

    @Modifying
    @Query(value = "update job set Company_Name=:companyName where id=:id",nativeQuery = true)
    void updateCompanyNameById(int id, String companyName);

    @Modifying
    @Query(value = "Delete from Job where Salary <= :salary",nativeQuery = true)
    void removeJobsLessThanSalary(Double salary);
}
