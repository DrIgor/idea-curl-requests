package drigor

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class ParserTest { // todo allowed header names and values

    @Test
    fun `basic parsing`() {
        with(parse("""curl 'http://example.com/fragment1/fragment2' -H 'Header1: value1' -H 'Header2: value2' -H 'Header3: value3'""")!!) {
            assertAll(
                    { assertThat(url).isEqualTo("http://example.com/fragment1/fragment2") },
                    { assertThat(headers).containsExactly("Header1", "value1", "Header2", "value2", "Header3", "value3") }
            )
        }
    }

    @Test
    fun `parse with method`() {
        val curl = """curl 'https://sds-front-dev1.51.144.37.142.nip.io/project-manager/api/project/0ba1f638-7c41-4d0d-850c-1c565e2da5d3/selection' -X PUT -H 'Pragma: no-cache' -H 'Origin: https://sds-front-dev1.51.144.37.142.nip.io' -H 'Accept-Encoding: gzip, deflate, br' -H 'Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7' -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjAxOTI1NjIsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX0RBVEFTQ0lFTkNFIiwiUk9MRV9HX0RMX0RfU0RTREhfTUFJTiJdLCJqdGkiOiJiZDYyMThkZS01ODM1LTQwMzgtYmY0Ni1lNTBlMTZlZjc2YTMiLCJjbGllbnRfaWQiOiJmcm9udGVuZCIsInNjb3BlIjpbInJlYWQiXX0.F7u_B3iVs8ypTHFk7MVZX1lb4g9l43IfVRmQ9EckTQ9a2UTWNmvZfCo8PyelLW9Y1k-u0FaWWlrYQkuPO-QFl-IYS1dKKqaXLHFacAqjFgh9wTX3ba84Y6p7qpbNRBU3e7su-z3Wyhlc_ntJ8J4ateOVi2mNHZCb_a5C6qAWUqNSlIjL4QqE-ET-XOM6kqRNNvaFXCjYUdHwuwXOx_aRQ7OaKq1vi-l_mj8AKDvGYZ4mGY4NF35zWPdw91hdMr8TP_-A4WRZQsWHOaDRZhZ-ED79oogvDWEvUEJfNtu5bx2GtZxgGBbLR3ZU0CKSzyuoh0IYxOUcwI1qWpjmk2IEaA' -H 'Content-Type: application/json;charset=UTF-8' -H 'Accept: */*' -H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36' -H 'Cache-Control: no-cache' -H 'Referer: https://sds-front-dev1.51.144.37.142.nip.io/project/0ba1f638-7c41-4d0d-850c-1c565e2da5d3' -H 'Cookie: cd270faceb6ffbc56c1c5c9b78282a2e=efe7cc605f45559dc85eeaf293653501; 37e3dab5a951f314f80f04c2734c410f=4f8dbdb627c9b9a2151295f75004b329' -H 'Connection: keep-alive' -H 'Expires: Sat, 01 Jan 2000 00:00:00 GMT' --data-binary '[{"id":"3345a5bc-a2f2-4c0a-952a-7f71fd0bc563","selected":true}]' --compressed"""
        println(parse(curl))
        assertAll()
    }

    @Test
    fun toIdeatest() {
        val curl = """curl 'https://sds-front-dev1.51.144.37.142.nip.io/project-manager/api/project/0ba1f638-7c41-4d0d-850c-1c565e2da5d3/selection' -X PUT -H 'Pragma: no-cache' -H 'Origin: https://sds-front-dev1.51.144.37.142.nip.io' -H 'Accept-Encoding: gzip, deflate, br' -H 'Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7' -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1NjAxOTI1NjIsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX0RBVEFTQ0lFTkNFIiwiUk9MRV9HX0RMX0RfU0RTREhfTUFJTiJdLCJqdGkiOiJiZDYyMThkZS01ODM1LTQwMzgtYmY0Ni1lNTBlMTZlZjc2YTMiLCJjbGllbnRfaWQiOiJmcm9udGVuZCIsInNjb3BlIjpbInJlYWQiXX0.F7u_B3iVs8ypTHFk7MVZX1lb4g9l43IfVRmQ9EckTQ9a2UTWNmvZfCo8PyelLW9Y1k-u0FaWWlrYQkuPO-QFl-IYS1dKKqaXLHFacAqjFgh9wTX3ba84Y6p7qpbNRBU3e7su-z3Wyhlc_ntJ8J4ateOVi2mNHZCb_a5C6qAWUqNSlIjL4QqE-ET-XOM6kqRNNvaFXCjYUdHwuwXOx_aRQ7OaKq1vi-l_mj8AKDvGYZ4mGY4NF35zWPdw91hdMr8TP_-A4WRZQsWHOaDRZhZ-ED79oogvDWEvUEJfNtu5bx2GtZxgGBbLR3ZU0CKSzyuoh0IYxOUcwI1qWpjmk2IEaA' -H 'Content-Type: application/json;charset=UTF-8' -H 'Accept: */*' -H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36' -H 'Cache-Control: no-cache' -H 'Referer: https://sds-front-dev1.51.144.37.142.nip.io/project/0ba1f638-7c41-4d0d-850c-1c565e2da5d3' -H 'Cookie: cd270faceb6ffbc56c1c5c9b78282a2e=efe7cc605f45559dc85eeaf293653501; 37e3dab5a951f314f80f04c2734c410f=4f8dbdb627c9b9a2151295f75004b329' -H 'Connection: keep-alive' -H 'Expires: Sat, 01 Jan 2000 00:00:00 GMT' --data-binary '[{"id":"3345a5bc-a2f2-4c0a-952a-7f71fd0bc563","selected":true}]' --compressed"""
        println(toIdeaRequest(parse(curl)!!))
    }
}
