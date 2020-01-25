'use-strict'



const functions = require('firebase-functions');

const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);



exports.sendNotification = functions.database.ref('/Notify/{user_id}').onWrite((change, context) => {

  /*

   * You can store values as variables from the 'database.ref'

   * Just like here, I've done for 'user_id' and 'notification'

   */
   const user_id = context.params.user_id;

   //const notification_id = context.params.notification_id;



   console.log('We have a notification to send to : ', context.params.user_id);
/*
    if(!context.data.val()){
            return console.log('A Notification has been deleted from the database');
      }
*/
   /* if(!user_id){
            return console.log('A Notification has been deleted from the database');
      }*/
     if(!change.after.val()){
      return console.log('Notification was deleted from the Database : ');
    }

   const deviceToken = admin.database().ref('/user/agency/'+ user_id +'/tokenID').once('value');
  // const deviceToken = admin.database().ref('/user/agency/user_id/tokenID').once('value');

   return deviceToken.then(result => {



    const token_id = result.val();

       console.log('We have Token : ', token_id);


    const payload = {

     notification: {

      title: "Tour Register",

      body: "A User just registered for one of your available tour",

      icon: "large"

     }

    };


    return admin.messaging().sendToDevice(token_id,payload).then(response => {

     return console.log('This was the notification feature');

    });

   });



});