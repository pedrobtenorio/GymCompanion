package com.pedro.GymCompanion.Security;

import lombok.*;


@Getter @Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class AccessToken {

    @NonNull
    private String accessToken;

}
