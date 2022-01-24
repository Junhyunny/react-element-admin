import {useState} from "react";
import {login} from "../../store/login/LoginStore";

const Login = () => {

    const [isInvalid, setIsInvalid] = useState(false);
    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');

    const onUserIdHandler = ({target: {value}}) => {
        setUserId(value);
    };

    const onPasswordHandler = ({target: {value}}) => {
        setPassword(value);
    };

    const onLoginHandler = () => {
        let invalid = false;
        if (userId === '' || password === '') {
            invalid = true;
        }
        setIsInvalid(invalid);
        if (invalid) {
            return;
        }
        login({
            userId,
            password
        });
    };

    return (
        <div>
            {isInvalid && userId === '' && <p>You should put user ID to login.</p>}
            <input placeholder="USER ID" value={userId} onChange={onUserIdHandler}/>
            {isInvalid && password === '' && <p>You should put password to login.</p>}
            <input placeholder="PASSWORD" type="password" value={password} onChange={onPasswordHandler}/>
            <div>
                <a>forgot your ID?</a>
                <a>forgot your password?</a>
            </div>
            <div>
                <button onClick={onLoginHandler}>Login</button>
                <button>Signup</button>
            </div>
        </div>
    );
};

export default Login;