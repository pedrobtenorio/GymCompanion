package com.pedro.GymCompanion.Security;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class Credential {

    @NonNull
    private String email;

    @NonNull
    private String password;

}
