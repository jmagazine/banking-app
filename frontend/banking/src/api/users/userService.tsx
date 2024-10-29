import { configDotenv } from "dotenv";
import { GetUserByUsernameAndPasswordPayload as GetUserByUsernameAndPasswordParams } from "../payloads";
import { User } from "./user";
import "dotenv";
configDotenv({ path: "src/api/.env.dev" });
let API_BASE_URL = process.env.API_BASE_URL;

const findUserWithCredentials = async (
  payload: GetUserByUsernameAndPasswordParams,
): Promise<User | any> => {
  try {
    const username = payload["username"];
    const password = payload["password"];
    const response = await fetch(
      `${API_BASE_URL}/users/login?username=${username}&password=${password}`,
      {
        method: "GET", // Ensure this is a GET request
        headers: {
          "Content-Type": "application/json",
        },
      },
    );
    if (!response.ok) {
      const errorText = await response.text();
      console.error(`Error fetching user: ${errorText}`);
      return { error: `${errorText}` };
    } else {
      const user: User = await response.json();
      return user;
    }
  } catch (error) {
    console.error(`Error occurred in findUserWithCredentials: ${error}`);
  }
};

export default findUserWithCredentials;
