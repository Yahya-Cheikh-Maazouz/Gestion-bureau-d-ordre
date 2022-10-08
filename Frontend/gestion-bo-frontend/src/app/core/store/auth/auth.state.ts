import ILoggedInUser from "../../models/ILoggedInUser";


const initialLoggedInUserState: ILoggedInUser = {
  isLoggedIn: false
}
export const authenticationFeatureKey = 'authenticatedUser';
export default initialLoggedInUserState
