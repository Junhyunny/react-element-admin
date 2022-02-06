import HttpClient from "../../utils/HttpClient";
import {login} from "./LoginStore";
import {waitFor} from "@testing-library/react";

describe('LoginStore test', () => {

    const requestBody = {
        userId: 'Junhyunny',
        password: '1234'
    };

    it('whenCallLoginMethod_thenCallPostMethod', () => {

        const spyHttpClient = jest.spyOn(HttpClient, 'post').mockResolvedValue({});

        login(requestBody);

        expect(spyHttpClient).toHaveBeenNthCalledWith(1, '/login', requestBody);
    });

    it('whenSuccessLogin_thenAccessTokenInLocalStorageIsNotNull', async () => {

        jest.spyOn(HttpClient, 'post').mockResolvedValue({
            tokenType: 'tokenType',
            accessToken: 'accessToken',
            refreshToken: 'refreshToken'
        });

        login(requestBody);

        await waitFor(() => {
            expect(localStorage.getItem('tokenType')).toEqual('tokenType');
        });
        expect(localStorage.getItem('accessToken')).toEqual('accessToken');
        expect(localStorage.getItem('refreshToken')).toEqual('refreshToken');
    });
});
