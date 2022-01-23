import {signup} from '../../store/signup/SignupStore';
import {useState} from "react";

const Signup = () => {

    const [isInvalid, setIsInvalid] = useState(false);
    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');
    const [confirmedPassword, setConfirmedPassword] = useState('');

    const onChangeIdHandler = ({target: {value}}) => {
        setUserId(value);
    };

    const onChangePasswordHandler = ({target: {value}}) => {
        setPassword(value);
    };

    const onChangeConfirmedPasswordHandler = ({target: {value}}) => {
        setConfirmedPassword(value);
    };

    const onSignupHandler = () => {
        let invalid = false;
        if (userId === '' || password === '' || confirmedPassword === '') {
            invalid = true;
        }
        if (password !== confirmedPassword) {
            invalid = true;
        }
        setIsInvalid(invalid);
        if(invalid) {
            return;
        }
        signup({
            userId,
            password
        });
    };

    return (
        <div>
            {isInvalid && !userId && <p>You should enter ID.</p>}
            <input placeholder="ID" onChange={onChangeIdHandler} value={userId}/>
            {isInvalid && !password && <p>You should enter password.</p>}
            {isInvalid && password !== confirmedPassword && <p>Please check password.</p>}
            <input placeholder="PASSWORD" type="password" onChange={onChangePasswordHandler} value={password}/>
            {isInvalid && !confirmedPassword && <p>You should enter confirm password.</p>}
            <input
                placeholder="CONFIRM PASSWORD"
                type="password"
                onChange={onChangeConfirmedPasswordHandler}
                value={confirmedPassword}/>
            <button onClick={onSignupHandler}>SIGNUP</button>
        </div>
    );
}

export default Signup;