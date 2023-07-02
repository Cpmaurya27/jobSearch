package com.example.job.searching.service;

import com.example.job.searching.model.Job;
import com.example.job.searching.model.JobType;
import com.example.job.searching.repository.IJobRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    IJobRepository iJobRepository;

    public List<Job> getAll() {
        return (List<Job>) iJobRepository.findAll();
    }

    public String saveJobs(List<Job> jobs) {
        iJobRepository.saveAll(jobs);
        return "Added successfully";
    }

    public String updateIfExist(Job job) {
        if(iJobRepository.existsById(job.getId())){
            iJobRepository.save(job);
            return "Updated successfully";
        }
        return "No such job with this JobId to update";
    }

    public String deleteJobById(int id) {
        if(iJobRepository.existsById(id)){
            iJobRepository.deleteById(id);
            return "Deleted Successfully";
        }return "NO jobs to delete with this Job id";
    }

    public List<Job> getAllJobsByJobType(JobType jobTypeEnum) {
        return iJobRepository.findByJobType(jobTypeEnum);
    }


    public List<Job> findByTitleContainingIgnoreCase(String title) {
        return iJobRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Job> findTop10ByOrderByAppliedDateDesc() {
        return iJobRepository.findTop10ByOrderByAppliedDateDesc();
    }

    public List<Job> findByCompanyNameContainingIgnoreCase(String companyName) {
        return iJobRepository.findByCompanyNameContainingIgnoreCase(companyName);
    }

    @Transactional
    public void updateJobTypeById(int id, JobType jobTypeEnum) {
        String jobType= jobTypeEnum.name();
        iJobRepository.updateJobTypeById(id,jobType);
    }

    @Transactional
    public void updateSalary(int id, Double salary) {
        iJobRepository.updateSalary(id,salary);
    }

    @Transactional
    public void updateCompanyNameById(int id, String companyName) {
        iJobRepository.updateCompanyNameById(id,companyName);
    }

    @Transactional
    public void removeJobsLessThanSalary(Double salary) {
        iJobRepository.removeJobsLessThanSalary(salary);
    }
}
