import {render, screen} from "@testing-library/react";
import userEvent from "@testing-library/user-event";

import Signup from "./Signup";
import * as SignupStore from '../../store/signup/SignupStore';

describe('test Signup', () => {

    it('whenRenderSignupPage_thenShowElements', () => {

        render(<Signup/>);

        expect(screen.getByPlaceholderText('ID')).toBeInTheDocument();
        expect(screen.getByPlaceholderText('PASSWORD')).toBeInTheDocument();
        expect(screen.getByPlaceholderText('PASSWORD').type).toEqual('password');
        expect(screen.getByPlaceholderText('CONFIRM PASSWORD')).toBeInTheDocument();
        expect(screen.getByPlaceholderText('CONFIRM PASSWORD').type).toEqual('password');
        expect(screen.getByText('SIGNUP')).toBeInTheDocument();
    });

    it('whenRenderSignupPage_thenNotShowErrorMessage', () => {

        render(<Signup/>);

        expect(screen.queryByText('You should enter ID.')).not.toBeInTheDocument();
        expect(screen.queryByText('You should enter password.')).not.toBeInTheDocument();
        expect(screen.queryByText('Please check password.')).not.toBeInTheDocument();
        expect(screen.queryByText('You should enter confirm password.')).not.toBeInTheDocument();
    });

    it('whenClickSignupButton_thenCallStoreSignupMethod', () => {

        const storeSpy = jest.spyOn(SignupStore, 'signup').mockResolvedValue({});
        render(<Signup/>);
        userEvent.type(screen.getByPlaceholderText('ID'), 'Junhyunny');
        userEvent.type(screen.getByPlaceholderText('PASSWORD'), '123');
        userEvent.type(screen.getByPlaceholderText('CONFIRM PASSWORD'), '123');

        userEvent.click(screen.getByText('SIGNUP'));

        expect(storeSpy).toHaveBeenCalledTimes(1);
        expect(storeSpy).toHaveBeenNthCalledWith(1, {
            userId: 'Junhyunny',
            password: '123'
        });
    });

    it('givenEmptyInput_whenClickSignupButton_thenShowErrorMessage', () => {

        render(<Signup/>);

        userEvent.click(screen.getByText('SIGNUP'));

        expect(screen.getByText('You should enter ID.')).toBeInTheDocument();
        expect(screen.getByText('You should enter password.')).toBeInTheDocument();
        expect(screen.getByText('You should enter confirm password.')).toBeInTheDocument();
        expect(screen.queryByText('Please check password.')).not.toBeInTheDocument();
    });

    it('givenWrongConfirmPassword_whenClickSignupButton_thenShowErrorMessage', () => {

        render(<Signup/>);
        userEvent.type(screen.getByPlaceholderText('ID'), 'Junhyunny');
        userEvent.type(screen.getByPlaceholderText('PASSWORD'), '123');
        userEvent.type(screen.getByPlaceholderText('CONFIRM PASSWORD'), '1234');

        userEvent.click(screen.getByText('SIGNUP'));

        expect(screen.getByText('Please check password.')).toBeInTheDocument();
    });
});