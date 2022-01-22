import {signup} from "./SignupStore";
import HttpClient from "../../utils/HttpClient";

describe('test SignupStore', () => {

    it('whenSignup_thenHttpClientPost', () => {

        const spyClient = jest.spyOn(HttpClient, 'post');
        const requestBody = {
            userId: 'Junhyunny',
            password: '123'
        };

        signup(requestBody);

        expect(spyClient).toBeCalledTimes(1);
        expect(spyClient).toHaveBeenNthCalledWith(1, '/signup', requestBody);
    });
});