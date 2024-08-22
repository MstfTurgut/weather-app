package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    label("user_saved")
    input {
        triggeredBy("triggerUserSaved()")
    }
    outputMessage {
        sentTo("user_saved")
        body("""
                {
                    "id": 1,
                    "city": "İstanbul",
                    "district": "Esenyurt"
                }
                """)
        headers {
            messagingContentType(applicationJson())
        }
    }

}