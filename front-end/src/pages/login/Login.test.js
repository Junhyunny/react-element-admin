import {render, screen} from "@testing-library/react";
import Login from "./Login";
import userEvent from "@testing-library/user-event";
import * as LoginStore from "../../store/login/LoginStore";

describe('test Login', () => {

    it('whenRender_thenElementsShouldBeInDocument', () => {

        render(<Login/>);

        expect(screen.getByPlaceholderText('USER ID')).toBeInTheDocument();
        expect(screen.getByPlaceholderText('PASSWORD')).toBeInTheDocument();
        expect(screen.getByPlaceholderText('PASSWORD').type).toEqual('password');
        expect(screen.queryByText('You should put user ID to login.')).not.toBeInTheDocument();
        expect(screen.queryByText('You should put password to login.')).not.toBeInTheDocument();
        expect(screen.getByText('forgot your ID?')).toBeInTheDocument();
        expect(screen.getByText('forgot your password?')).toBeInTheDocument();
        expect(screen.getByRole('button', {
            name: 'Login'
        })).toBeInTheDocument();
        expect(screen.getByRole('button', {
            name: 'Signup'
        })).toBeInTheDocument();
    });

    it('givenEmptyText_whenClickLoginButton_thenWarningMessage', () => {

        render(<Login/>);

        userEvent.click(screen.getByRole('button', {
            name: 'Login'
        }));

        expect(screen.getByText('You should put user ID to login.')).toBeInTheDocument();
        expect(screen.getByText('You should put password to login.')).toBeInTheDocument();
    });

    it('givenUserIdAndPassword_whenClickLoginButton_thenCallLoginMethod', () => {

        const spyLoginStore = jest.spyOn(LoginStore, 'login').mockResolvedValue({});

        render(<Login/>);

        userEvent.type(screen.getByPlaceholderText('USER ID'), 'Junhyunny');
        userEvent.type(screen.getByPlaceholderText('PASSWORD'), '123');
        userEvent.click(screen.getByRole('button', {
            name: 'Login'
        }));

        expect(spyLoginStore).toHaveBeenNthCalledWith(1, {
            userId: 'Junhyunny',
            password: '123'
        });
    });

    it('givenEmptyText_whenClickLoginButton_thenDoNotCallLoginMethod', () => {

        const spyLoginStore = jest.spyOn(LoginStore, 'login').mockResolvedValue({});

        render(<Login/>);

        userEvent.click(screen.getByRole('button', {
            name: 'Login'
        }));

        expect(spyLoginStore).toHaveBeenCalledTimes(0);
    });


});