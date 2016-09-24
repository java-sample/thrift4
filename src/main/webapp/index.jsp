<jsp:useBean id="greeter" class="org.gradle.sample.Greeter"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
    </head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/store.js/1.3.20/store.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/cryptico/0.0.1343522940/cryptico.min.js"></script>

    <p>${greeter.greeting}</p>
    jjj
    <%
    greeter.init(request, response, out);
    out.println(new java.util.Date()+"<br />");
    greeter.saveProps();
    greeter.mysqlTest();
    out.println(new java.util.Date()+"<br />");
    greeter.sqliteTest();
    out.println(new java.util.Date()+"<br />");
    greeter.ormliteTest();
    out.println(new java.util.Date()+"<br />");
    out.println(response.getClass().getName());
    out.println(out.getClass().getName());
    out.println(greeter.getPublicKeyBase64());
    %>

    <!-- <script>alert($.cookie('PublicKey'));</script> -->
    <script>
        // The passphrase used to repeatably generate this RSA key.
        var PassPhrase = "The Moon is a Harsh Mistress.";
        // The length of the RSA key, in bits.
        var Bits = 1024;
        var MattsRSAkey = cryptico.generateRSAKey(PassPhrase, Bits);
        //alert(JSON.stringify(MattsRSAkey));

        var MattsPublicKeyString = $.cookie('PublicKey');
        //var PlainText = "Matt, I need you to help me with my Starcraft strategy.";
        var PlainText = "Matt";
        var EncryptionResult = cryptico.encrypt(PlainText, MattsPublicKeyString);
        alert(EncryptionResult.cipher);
        //var DecryptionResult = cryptico.decrypt(EncryptionResult.cipher, $.cookie('PrivateKey'));
        //alert(DecryptionResult.plaintext);
        //alert($.cookie('PublicKey'));
    </script>

    <script>
        function mySubmit(formName, url, method)
        {
            // ??????????????
            var f = document.forms[formName];
            document.getElementById('hidden').value = EncryptionResult.cipher;

            f.method = method; // method(GET or POST)?????
            f.action = url; // action(???URL)?????
            f.submit(); // submit ??
            return true;
        }
    </script>

    <form name="myForm" action="form.html" method="GET">
        <input type="submit" value="submit"><br />
        <input type="hidden" id="hidden" name="hidden" value="abc"><br />
        <input type="button" value="button" onclick="return mySubmit('myForm', '', 'GET');">
    </form>

</html>
