package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Model.JopApplication;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.JopApplicationRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JopApplicationService {

    private final JopApplicationRepository jopApplicationRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;


    public List<JopApplication> getJopApplication(){
        return jopApplicationRepository.findAll();
    }

    public Boolean ApplyForJob(JopApplication jopApplication){
        List<User>users=userRepository.findAll();
        List<JobPost> jobPosts =jobPostRepository.findAll();
        for( User user: users){
            if(user.getId()==jopApplication.getUserid()){
                for(JobPost jobPost:jobPosts){
                    if(jobPost.getId()==jopApplication.getJobpostid()){
                        jopApplicationRepository.save(jopApplication);
                        return true;
                    }
                }
            }
        }

        return false;

    }


    public Boolean WithdrawJobApplication(Integer id){

        JopApplication jopApplication = jopApplicationRepository.getById(id);
        if(jopApplication==null){

            return false;
        }

        jopApplicationRepository.delete(jopApplication);
        return true;
    }



}
