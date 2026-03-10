import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Film } from './film';

describe('Film', () => {
  let component: Film;
  let fixture: ComponentFixture<Film>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Film],
    }).compileComponents();

    fixture = TestBed.createComponent(Film);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
