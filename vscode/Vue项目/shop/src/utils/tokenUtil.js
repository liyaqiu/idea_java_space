import { v4 as uuidv4 } from 'uuid';
export const genAnonymousToken = function(){
    let anonymousToken = localStorage.getItem('anonymousToken')
    if(!anonymousToken){
        anonymousToken = uuidv4()
        localStorage.setItem('anonymousToken',anonymousToken)
    }
    return anonymousToken
}