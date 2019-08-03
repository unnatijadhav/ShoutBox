import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchFriendFilter'
})
export class SearchFriendFilterPipe implements PipeTransform {

  transform(value: any[], searchString: string) {

    if (!searchString) {
      console.log('no search');
      console.log(searchString);
      return value;
    }
    return value.filter(data => {
      const firstName = data.owner.firstName.toLowerCase().includes(searchString.toLowerCase());
      const lastName = data.owner.lastName.toLowerCase().includes(searchString.toLowerCase());  
      return (firstName + lastName);
    })
  }

}
