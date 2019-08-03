import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterInitials'
})
export class FilterInitialsPipe implements PipeTransform {

  transform(value: any[], searchString: string) {

    if (!searchString) {
      console.log(searchString);
      return value;
    }
    return value.filter(data => 
      {
      const initails = data.substring(0,1);
      return initails;
    })
  }

}
