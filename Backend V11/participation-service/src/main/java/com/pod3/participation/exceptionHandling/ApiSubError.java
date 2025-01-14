package com.pod3.participation.exceptionHandling;

abstract class ApiSubError {

}

//@Data
//@EqualsAndHashCode(callSuper = false)
//@AllArgsConstructor
class ApiValidationError extends ApiSubError {
   private String object;
   private String field;
   private Object rejectedValue;
   private String message;

   ApiValidationError(String object, String message) {
       this.object = object;
       this.message = message;
   }
}