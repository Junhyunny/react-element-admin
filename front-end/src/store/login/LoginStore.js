import HttpClient from "../../utils/HttpClient";

export const login = (requestBody) => {
    HttpClient.post('/login', requestBody)
        .then(result => {
            localStorage.setItem('tokenType', result['tokenType']);
            localStorage.setItem('accessToken', result['accessToken']);
            localStorage.setItem('refreshToken', result['refreshToken']);
        });
};