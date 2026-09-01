function sum( a, b){

    const u="Aditya1424";
    const p="password@1234"

    if((a==u )&&(b==p)){

        return "Login successfully";
    }
    else{

        return "Invalid user detail";

    }
}

function checkPassword(password) {

    let upper = false;
    let lower = false;
    let number = false;
    let special = false;

    if (password.length < 8) {
        return false;
    }

    for (let ch of password) {

        if (ch >= 'A' && ch <= 'Z') {
            upper = true;
        }
        else if (ch >= 'a' && ch <= 'z') {
            lower = true;
        }
        else if (ch >= '0' && ch <= '9') {
            number = true;
        }
        else {
            special = true;
        }
    }

    return upper && lower && number && special;
}



    function sumwithMsg(clbk,msg){

        let h=prompt("Enter the user name");
        let k=prompt("Enter the password");

        document.write(checkPassword(k));

        const

        if()




        const result=clbk(h,k);
        const fresult="Hyy "+msg+" " +result;

        console.log(fresult);
        document.write(fresult);
        // 

    }

    sumwithMsg(sum,"Mr.Deepak");
