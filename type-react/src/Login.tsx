import axios from "axios";
import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import SetBearerToken from "./API/setBearerToken";


type FormData = {
  userName: string;
  password: string;
};

export default function Login() {
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [bearerToken, setBearerToken] = useState('')

    const {
    register,
    handleSubmit,
    } = useForm<FormData>();


    async function getBearerToken(username: string, password: string) {
        try{
        const response =  await axios.post('http://127.0.1.1:8888/api/authenticate', {
            username:  userName,
            password: password
        }
        );           
        
        console.log(response)
        setBearerToken(response.data.id_token)
        return response.data.id_token;
    
        }catch(e){
            console.log(e)
        }
    }   


    const onSubmit = handleSubmit(({ userName, password }) => {
        setUserName(userName)
        setPassword(password)
        getBearerToken(userName, password);

    }); 
    return (
        <form onSubmit={onSubmit}>
        <label>First Name</label>
        <input {...register("userName")} />
        <label>Last Name</label>
        <input {...register("password")} />
        <input type="submit" />
        </form>
    );
}
