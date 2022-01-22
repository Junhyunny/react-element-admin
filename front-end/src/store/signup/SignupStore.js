import HttpClient from '../../utils/HttpClient';

export const signup = (requestBody) => {
    return HttpClient.post('/signup', requestBody)
        .then(result => {
            return result;
        })
        .catch(error => {
            return error;
        });
};
