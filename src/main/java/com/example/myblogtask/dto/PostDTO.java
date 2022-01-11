package com.example.myblogtask.dto;

import com.example.myblogtask.models.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private String title;
    private String body;


}
