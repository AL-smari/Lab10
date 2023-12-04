package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JopApplication;
import com.example.lab10.Service.JopApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobApplication")
@RequiredArgsConstructor
public class JopApplicationController {

    private final JopApplicationService jopApplicationService;


    @GetMapping("/get")
    public ResponseEntity getJobApplication(){
        return ResponseEntity.status(HttpStatus.OK).body(jopApplicationService.getJopApplication());
    }

    @PostMapping("/add")
   public ResponseEntity ApplyForJob(@Valid @RequestBody JopApplication jopApplication ,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(message));
        }
      if(jopApplicationService.ApplyForJob(jopApplication)){
          return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("job apply"));
      }return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("id not found"));
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity WithdrawApplication(@PathVariable Integer id){

        if(jopApplicationService.WithdrawJobApplication(id)){
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("withdraw application"));
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("id not found"));
   }
}
