import ILoggedInUser from "../models/ILoggedInUser";


export function mapToLoggedInUser(authenticationResponse:any): ILoggedInUser {
  console.log(authenticationResponse)
    return {
        email: authenticationResponse.body.email,
        isLoggedIn: true,
        token: authenticationResponse.headers.get("Authorization")
    };
}

export function mapSessionToUser(authenticationResponse:any): ILoggedInUser {
    return {
        email: authenticationResponse.email,
        isLoggedIn: true,
        token: authenticationResponse.xa
    };
}
