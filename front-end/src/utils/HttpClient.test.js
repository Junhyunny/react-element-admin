import axios from "axios";
import HttpClient from "./HttpClient";

describe('test HttpClient', () => {

    it('whenCallPost', () => {

        const spyAxios = jest.spyOn(axios, 'post').mockResolvedValue({});
        const requestBody = {
            message: 'hello world'
        }
        const params = {
            page: 0,
            size: 10
        }

        HttpClient.post('/test', requestBody, params);

        expect(spyAxios).toHaveBeenNthCalledWith(1, '/backend_service/test',requestBody, {
            params
        });
    });
});