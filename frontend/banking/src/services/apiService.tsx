const API_BASE_URL = "https://localhost:8080/api/v1";
import { GetUserByUsernameAndPasswordPayload } from "./payloads";
import { User } from "./user";

const findUserWithCredentials = async (
  payload: GetUserByUsernameAndPasswordPayload,
): Promise<User | any> => {
  try {
    console.log(payload);
    const username = payload["username"];
    const password = payload["password"];
    const response = await fetch(
      `http://localhost:8080/api/v1/users/login?username=${username}&password=${password}`,
      {
        method: "GET", // Ensure this is a GET request
        headers: {
          "Content-Type": "application/json",
        },
      },
    );
    const user = await response.json();
    user["dob"] = new Date(user["dob"]);
    return user;
  } catch (error) {
    throw new Error(`Error fetching user: ${error}`);
  }
};

export default findUserWithCredentials;
