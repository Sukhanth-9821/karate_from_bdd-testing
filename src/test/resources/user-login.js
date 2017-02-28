function(credential) {
    var request = {
        url: baseUrl+'/user/login',
        method: 'post',
        body: credential
    };
    var response = karate.request(request);
    if (response.status != 200) {
        throw ('login failed: ' + response);
    }
    
    return response.body.authToken;
}