/**
 * Created with IntelliJ IDEA.
 * User: changhwo
 * Date: 13. 6. 16.
 * Time: 오전 7:30
 * To change this template use File | Settings | File Templates.
 */
$(function () {

    $.validator.setDefaults({
        onkeyup: false,
        showErrors: function (errorMap, errorList) {
            notifyMessage("error", errorList[0].message);
            validate.focusInvalid();
        }
    });

    $.validator.addMethod("alphanumeric", function (value, element) {
            return /^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$/.test(value);
    });

    var validate = $("#signupform").validate({
        rules: {
            userName: "required",
            userId: {
                required: true,
                remote: {
                    url: "/user/duplicateId",
                    type: "post"

                }
            },
            password: {
                required: true,
                minlength: 8,
                alphanumeric: true
            },
            confirm: {
                required: true,
                minlength: 8,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true
            }
        },
        messages: {
            userName: "이름을 입력해 주세요",
            userId: {
                required: "ID를 입력해주세요",
                remote: "중복된 ID 입니다"
            },
            password: {
                required: "암호를 입력해 주세요",
                minlength: "암호는 8자 이상이어야 합니다.",
                alphanumeric: "비밀번호는 영문 숫자 혼용입니다."
            },
            confirm: {
                required: "암호를 한 번 더 입력해 주세요",
                minlength: "암호는 8자 이상이어야 합니다.",
                equalTo: "암호가 일치하지 않습니다."
            },
            email: "형식에 맞는 이메일을 입력해 주세요."
        }
    });
});
