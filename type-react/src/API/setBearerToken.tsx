import axios from "axios";
import { useState } from "react";
import { useParams } from "react-router";

const SetBearerToken: React.FC = (props) => {
    console.log(props)

    useState(() => {
        getBearerToken();
        console.log('holis')
                })

    const [bearerToken, setBearerToken] = useState('')

    async function getBearerToken() {
        try{
        const response =  await axios.post('http://127.0.1.1:8888/api/authenticate', {
            userame:  'admin',
            password: 'admin'
        }
        );           
         
        setBearerToken(response.data.id_token)
        return response.data.id_token;
        }catch(e){
            console.log(e)
        }
    }   
   
    return(null);

}
export default SetBearerToken;