package com.geveit.melod.song;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SongInput {
    @NotBlank(message = "Working Title is required")
    @Min(4)
    private String workingTitle;
}
