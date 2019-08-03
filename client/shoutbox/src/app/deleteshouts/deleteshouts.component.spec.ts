import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteshoutsComponent } from './deleteshouts.component';

describe('DeleteshoutsComponent', () => {
  let component: DeleteshoutsComponent;
  let fixture: ComponentFixture<DeleteshoutsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeleteshoutsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteshoutsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
