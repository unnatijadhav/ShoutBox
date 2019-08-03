import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterNames'
})
export class FilterNamesPipe implements PipeTransform {

  transform(value: any[], searchString: string) {

    if (!searchString) {
      console.log('no search');
      console.log(searchString);
      return value;
    }
    return value.filter(data => {
      const firstName = data.firstName.toLowerCase().includes(searchString.toLowerCase());
      const lastName = data.lastName.toLowerCase().includes(searchString.toLowerCase());  
      return (firstName + lastName);
    })
  }
}
